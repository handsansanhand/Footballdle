# beginning of the data pipeline
# download the data
# structure the data according to what im looking for
# -> name , age , nationality , position , market value? , club , league
# then store the data in a persistent database
import pandas as pd
import kagglehub
import os
import country_converter as coco
import pycountry
#download latest version

pos_map = {
    'FW': 'Forward',
    'MF': 'Midfielder',
    'DF': 'Defender',
    'GK': 'Goalkeeper'
}
#some codes dont map, need to manually set them here
manual_country_map = {
    'GER': 'Germany',
    'CHI': 'Chile',
    'MAD': 'Madagascar',
    'GUI': 'Guinea',
    'KSA': 'Saudi Arabia',
    'TOG': 'Togo',
    'URU': 'Uruguay',
    'CTA': 'Catalonia',
    'NED': 'Netherlands',
    'WAL': 'Wales',
    'EQG': 'Equatorial Guinea',
    'ANG': 'Angola',
    'CGO': 'Republic of the Congo',
    'POR': 'Portugal',
    'CRC': 'Costa Rica',
    'HAI': 'Haiti',
    'ZIM': 'Zimbabwe',
    'SUI': 'Switzerland',
    'PAR': 'Paraguay',
    'BAN': 'Bangladesh',
    'CRO': 'Croatia',
    'PUR': 'Puerto Rico',
    'GRE': 'Greece',
    'DEN': 'Denmark',
    'KVX': 'Kosovo',            
    'GAM': 'Gambia',
    'SCO': 'Scotland',
    'ZAM': 'Zambia',
    'ENG': 'England',
    'ALG': 'Algeria',
    'NIR': 'Northern Ireland',
    'PHI': 'Philippines',
}

def map_positions(pos_str):
    positions = [p.strip() for p in pos_str.split(',')]
    mapped = [pos_map.get(p, p) for p in positions]
    return ', '.join(mapped)

def convert_to_country_name(code):
    if pd.isnull(code):
        return None
    try:
        country = pycountry.countries.get(alpha_3=code)
        if country:
            return country.name
        else:
            if code in manual_country_map:
                return manual_country_map[code]
            else:
                return None
    except LookupError:
        return None

def main():
    path = kagglehub.dataset_download("hubertsidorowicz/football-players-stats-2024-2025")

    csv_file = 'players_data-2024_2025.csv'
    csv_path = os.path.join(path, csv_file)

    #read the csv file
    df = pd.read_csv(csv_path)

    #now extract the columns to keep, filter it to another csv
    columns_to_keep = ['Player', 'Age', 'Nation', 'Pos', 'Squad', 'Comp', 'MP', 'Gls', 'Ast']

    df_filtered = df[columns_to_keep]

    df_filtered.to_csv('dataPipeline/data/raw/raw_data.csv', index=False)

    #players have played for more than two clubs, is a problem
    #convert the matches played to numeric
    #for each player, sort them by their matches played, so the team theyve played for is at the top
    #cut off all the other duplicates
    df_filtered['MP'] = pd.to_numeric(df_filtered['MP'], errors='coerce').fillna(0)
    df_filtered_sorted = df_filtered.sort_values(['Player', 'MP'], ascending=[True, False])
    df_unique = df_filtered_sorted.drop_duplicates(subset=['Player'], keep='first')

    #convert the position prefrixes to the english names
    df_unique = df_filtered.drop_duplicates(subset='Player', keep='last').copy()
    df_unique.loc[:, 'Pos'] = df_unique['Pos'].apply(map_positions)
    df_unique['Nation'] = df_unique['Nation'].str.split().str[1]

    #clean up the nation field
    df_unique['Nation'] = df_unique['Nation'].apply(convert_to_country_name)
    df_unique['Comp'] = df_unique['Comp'].str.split(' ', n=1).apply(lambda x: x[1] if len(x) > 1 else None)
    #remove any nan entries in the columns
    df_clean = df_unique.dropna()
    #and finally save the big dataset
    df_clean.to_csv('dataPipeline/data/processed/overall_players_data.csv', index=False)

if __name__ == "__main__":
    main()
