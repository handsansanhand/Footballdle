


export const makeGuess = async (guessedPlayerName, league) => {
    console.log("Making guess for ", guessedPlayerName , " for league ", league);
    try {
        const res = await fetch('/guess/players/guess', {
            method: 'POST',
            headers: {
            'Content-Type': 'application/json'
            },
            body: JSON.stringify({
            sessionId: '1',  
            playerName: guessedPlayerName,
            league: league 
            })
        })

        const data = await res.json()
        alert(data);
        return data;
        } catch (error) {
        console.error('Error making guess:', error)
        }
    }