from scripts import initialize_data, start_database, split_data, populate_database, populate_database_table
from config import config
def main():
    initialize_data.main()
    split_data.main()
    populate_database.main()

if __name__ == "__main__":
    main()