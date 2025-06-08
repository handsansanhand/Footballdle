# config.py

import os

#base data directory
BASE_DATA_DIR = os.path.join('dataPipeline', 'data')

#paths for raw and processed data
RAW_DATA_PATH = os.path.join(BASE_DATA_DIR, 'raw', 'raw_data.csv')
PROCESSED_DATA_PATH = os.path.join(BASE_DATA_DIR, 'processed', 'overall_players_data.csv')

#kaggle dataset details
KAGGLE_DATASET = "hubertsidorowicz/football-players-stats-2024-2025"
KAGGLE_CSV_FILE = 'players_data-2024_2025.csv'

#database
DATABASE_NAME = "footballdle_db"
POSTGRES_USER = os.environ['POSTGRES_USER']
POSTGRES_PASSWORD = os.environ['POSTGRES_PASSWORD']
