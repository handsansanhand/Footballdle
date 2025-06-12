import { useState } from "react";
import Button from "react-bootstrap/esm/Button";




function TestButton( {onGuessMade} ) {

    const[testguess, setTestGuess] = useState("");

    const result = {
    guessed_player : {
        id: 1034,
        player: "Erling Haaland",
        age: 24,
        nation: "Norway",
        position: "Forward, Midfielder",
        team: "Manchester City",
        league: "Premier League",
        matchesPlayed: 31,
        goals: 22,
        assists: 3   
    },
    guess_result : {
        matchesPlayed: "Higher",
        nation: "Incorrect",
        assists: "Lower",
        league: "Correct",
        id: "Lower",
        position: {
            Forward: "Correct",
            Midfielder: "Incorrect"
        },
        team: "Incorrect",
        age: "Lower",
        player: "Incorrect",
        goals: "Lower"
    }   
};

    const handleMakeTestGuess = async () => {
        if(testguess == "") {
            setTestGuess(JSON.stringify(result));
             onGuessMade(testguess);
        }
        else {
              setTestGuess("");
             onGuessMade(testguess);
        }
    }
    return (
        <>
          <div style={{ marginTop: "20px" }}>
      <Button onClick={handleMakeTestGuess}>TEST</Button>
    </div>
        </>
    );
}
export default TestButton;