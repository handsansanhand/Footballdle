from config import config
from . import populate_database_table

def main():
    populate_database_table.populate(config.PROCESSED_DATA_PATH, "overall_players_table")
    populate_database_table.populate(config.PREMIER_LEAGUE_DATA_PATH, "premier_league_players_table")
    populate_database_table.populate(config.LA_LIGA_DATA_PATH, "la_liga_players_table")
    populate_database_table.populate(config.BUNDESLIGA_DATA_PATH, "bundesliga_players_table")
    populate_database_table.populate(config.SERIE_A_DATA_PATH, "serie_a_players_table")
    populate_database_table.populate(config.LIGUE_1_DATA_PATH, "ligue_1_players_table")
if __name__ == "__main__":
    main()