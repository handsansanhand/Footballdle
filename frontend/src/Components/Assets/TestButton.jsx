import { useState } from "react";
import Button from "react-bootstrap/esm/Button";




function TestButton( {onGuessMade} ) {

    const[testguess, setTestGuess] = useState("");

    const result = {
    matchesPlayed: "Higher",
    nation: "Incorrect",
    assists: "Lower",
    league: "Correct",
    id: "Lower",
    position: {
        Forward: "Correct"
    },
    team: "Incorrect",
    age: "Lower",
    player: "Incorrect",
    goals: "Lower"
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