import os

# Base data directory (relative inside container/project)
BASE_DATA_DIR = os.path.join('dataPipeline', 'data')

# ensure the raw and processed directories exist
RAW_DIR = os.path.join(BASE_DATA_DIR, 'raw')
PROCESSED_DIR = os.path.join(BASE_DATA_DIR, 'processed')
os.makedirs(RAW_DIR, exist_ok=True)
os.makedirs(PROCESSED_DIR, exist_ok=True)

# file paths for raw and processed data
RAW_DATA_PATH = os.path.join(RAW_DIR, 'raw_data.csv')
PROCESSED_DATA_PATH = os.path.join(PROCESSED_DIR, 'overall_players_data.csv')
PREMIER_LEAGUE_DATA_PATH = os.path.join(PROCESSED_DIR, 'premier_league_players_data.csv')
LA_LIGA_DATA_PATH = os.path.join(PROCESSED_DIR, 'la_liga_players_data.csv')
BUNDESLIGA_DATA_PATH = os.path.join(PROCESSED_DIR, 'bundesliga_players_data.csv')
LIGUE_1_DATA_PATH = os.path.join(PROCESSED_DIR, 'ligue_1_players_data.csv')
SERIE_A_DATA_PATH = os.path.join(PROCESSED_DIR, 'serie_a_players_data.csv')

#dataset details
KAGGLE_DATASET = "hubertsidorowicz/football-players-stats-2024-2025"
KAGGLE_CSV_FILE = 'players_data-2024_2025.csv'

#database configuration (environment variables with fallbacks)
DATABASE_NAME = os.getenv('POSTGRES_DB', 'footballdle_db')
POSTGRES_USER = os.getenv('POSTGRES_USER', 'youruser')
POSTGRES_PASSWORD = os.getenv('POSTGRES_PASSWORD', 'yourpassword')
POSTGRES_HOST = os.getenv('POSTGRES_HOST', 'localhost')
POSTGRES_PORT = os.getenv('POSTGRES_PORT', '5432')
