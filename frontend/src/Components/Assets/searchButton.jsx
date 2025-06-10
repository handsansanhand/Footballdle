import React, { useState } from "react";

function SearchButton() {
  const [count, setCount] = useState(0)
  const [response, setResponse] = useState(null)

  const handleGuessClick = async () => {
    try {
      const res = await fetch('/api/players/guess', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          sessionId: '1',  // replace with your actual session ID logic
          playerName: 'Erling Haaland',
          league: 'Premier League' // replace if needed
        })
      })

      const data = await res.json()
      setResponse(data)
      alert(data)
    } catch (error) {
      console.error('Error making guess:', error)
      setResponse({ error: 'Something went wrong.' })
    }
  }

  return (
    <>
      <p className="read-the-docs">
        Click on the button to make a guess!
      </p>
      <button onClick={handleGuessClick}>
        Make a Guess
      </button>
      {response && (
        <pre>{JSON.stringify(response, null, 2)}</pre>
      )}
    </>
  )
  
}

export default SearchButton