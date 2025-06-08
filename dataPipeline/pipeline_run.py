from scripts import initialize_data, start_database

def main():
    initialize_data.main()
    #
    start_database.main()

if __name__ == "__main__":
    main()