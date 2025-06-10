

export const fetchPlayerNames = async (leagueName) => {
    try {
        const response = await fetch(`/players/names/${leagueName}`);
        if(!response.ok) {
            throw new Error("Failed to fetch players");
        }
        const data = await response.json();
        return data;
    }
    catch(error) {
        console.error("Error fetching player names: ", error );
        return [];
    }
}