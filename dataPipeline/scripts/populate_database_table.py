import config as config
import pandas as pd
import psycopg2 as psql
from config import config
def populate(csv_path: str, table_name: str):
    df = pd.read_csv(csv_path, encoding='utf-8')

    
    conn = psql.connect(database=config.DATABASE_NAME,
                        host=config.POSTGRES_HOST,
                        user=config.POSTGRES_USER,
                        password=config.POSTGRES_PASSWORD,
                        port=5432)
    conn.set_client_encoding('UTF8')
    cursor = conn.cursor()

    cursor.execute(f"DROP TABLE IF EXISTS {table_name};")
    #Player,Age,Nation,Pos,Squad,Comp,MP,Gls,Ast
    cursor.execute(f"""
        CREATE TABLE {table_name} (
            id SERIAL PRIMARY KEY,
            player VARCHAR(100),
            age INT,
            nation VARCHAR(100),
            position VARCHAR(100),
            team VARCHAR(100),
            league VARCHAR(100),
            matches_played INT,
            goals INT,
            assists INT
        );
    """)
    conn.commit()

    for _, row in df.iterrows():
        cursor.execute(f"""
            INSERT INTO {table_name} (player, age, nation, position, team, league, matches_played, goals, assists)
            VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s)
        """, (
            row['Player'],
            int(row['Age']),
            row['Nation'],
            row['Pos'],
            row['Squad'],
            row['Comp'],
            int(row['MP']),
            int(row['Gls']),
            int(row['Ast'])
        ))  
    conn.commit()

    #close connection
    cursor.close()
    conn.close()
    print(f"Data written to {table_name} successfully!")