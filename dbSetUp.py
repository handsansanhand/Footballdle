# beginning of the data pipeline
# download the data
# structure the data according to what im looking for
# -> name , age , nationality , position , market value? , club , league
# then store the data in a persistent database
import pandas as pd
import kagglehub
import os

#download latest version
path = kagglehub.dataset_download("hubertsidorowicz/football-players-stats-2024-2025")

print("Path to dataset files:", path)
print("Contents of path:", os.listdir(path))

csv_file = 'players_data-2024_2025.csv'
csv_path = os.path.join(path, csv_file)
print("CSV files found:", csv_path)

#read the csv file
df = pd.read_csv(csv_path)

#now extract the columns to keep, filter it to another csv
columns_to_keep = ['Player', 'Age', 'Nation', 'Pos', 'Squad', 'Comp', 'MP', 'Gls', 'Ast']

df_filtered = df[columns_to_keep]

print(df_filtered.columns)
print(df.head)

#players have played for more than two clubs, is a problem
#convert the matches played to numeric
#for each player, sort them by their matches played, so the team theyve played for is at the top
#cut off all the other duplicates
df_filtered['MP'] = pd.to_numeric(df_filtered['MP'], errors='coerce').fillna(0)
df_filtered_sorted = df_filtered.sort_values(['Player', 'MP'], ascending=[True, False])
df_unique = df_filtered_sorted.drop_duplicates(subset=['Player'], keep='first')

print(df_unique)
df_unique.to_csv('overall_players_data.csv', index=False)
