from scripts import initialize_data, start_database, split_data

def main():
    initialize_data.main()
    split_data.main()
    start_database.main()

if __name__ == "__main__":
    main()