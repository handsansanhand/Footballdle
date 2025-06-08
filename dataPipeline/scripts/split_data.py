import pandas as pd
from config import config
#splits data based on league
def main():
 overall_df = pd.read_csv(config.PROCESSED_DATA_PATH, encoding='utf-8')
 premier_league_df = overall_df[overall_df['Comp'] == 'Premier League']
 la_liga_df = overall_df[overall_df['Comp'] == 'La Liga']
 bundesliga_df = overall_df[overall_df['Comp'] == 'Bundesliga']
 ligue_1_df = overall_df[overall_df['Comp'] == 'Ligue 1']
 serie_a_df = overall_df[overall_df['Comp'] == 'Serie A']
 
 premier_league_df.to_csv(config.PREMIER_LEAGUE_DATA_PATH, index=False, encoding='utf-8')
 la_liga_df.to_csv(config.LA_LIGA_DATA_PATH, index=False, encoding='utf-8')
 bundesliga_df.to_csv(config.BUNDESLIGA_DATA_PATH, index=False, encoding='utf-8')
 ligue_1_df.to_csv(config.LIGUE_1_DATA_PATH, index=False, encoding='utf-8')
 serie_a_df.to_csv(config.SERIE_A_DATA_PATH, index=False, encoding='utf-8')

 print("Data split successfully!")
 if __name__ == "__main__":
    main()