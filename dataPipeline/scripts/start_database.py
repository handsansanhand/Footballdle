import os
import psycopg2 as psql
import pandas as pd
from config import config
def main():
    overall_df = pd.read_csv(config.PROCESSED_DATA_PATH, encoding='utf-8')
    postgres_user = config.POSTGRES_USER
    postgres_password = config.POSTGRES_PASSWORD
    
    conn = psql.connect(database=config.DATABASE_NAME,
                        host="localhost",
                        user=postgres_user,
                        password=postgres_password,
                        port=5432)
    conn.set_client_encoding('UTF8')
    cursor = conn.cursor()
    
    cursor.execute("DROP TABLE IF EXISTS overall_players;")
    #Player,Age,Nation,Pos,Squad,Comp,MP,Gls,Ast
    cursor.execute("""
        CREATE TABLE overall_players (
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

    for _, row in overall_df.iterrows():
        cursor.execute("""
            INSERT INTO overall_players (player, age, nation, position, team, league, matches_played, goals, assists)
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
    print("Data written successfully!")
if __name__ == "__main__":
    main()