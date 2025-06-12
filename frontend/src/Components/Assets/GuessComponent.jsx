import './GuessComponent.css'
import { parseString } from "../Services/parseString";
import { parseResult } from '../Services/parseResult';
import { ImCross } from "react-icons/im";

function GuessComponent( {guessResult} ) {
    if(!guessResult) {
        return (
             <>
          <div className='guess-container' style={{ marginTop: "20px" }}>
      <h3>Guess Result:</h3>
      <p>{"Make a guess to see the result here."}</p>
    </div>
        </>
        )
    }
    const parsedGuess = guessResult ? JSON.parse(guessResult) : null;
    const guessedPlayer = parsedGuess ? parsedGuess.guessed_player : null;
    const guessResultData = parsedGuess ? parsedGuess.guess_result : null;
    return (
        <>
        <div className='guess-container' style={{ marginTop: "20px" }}>
            <h3>Guess Result: {guessedPlayer.player}</h3>
                <div className='guess-grid'>
                    {Object.entries(guessedPlayer)
                    .filter(([key]) => key !== 'id' && key !== 'player')
                    .map( ([key,value]) => (
                        <div key={key} className='guess-element'> 
                            <div className='guess-element-title'>{parseString(key)}</div>
                            <div className='guess-element-guessed-value'>
                                {typeof value === 'object' ? JSON.stringify(value) : value}
                                <br></br>
                                {typeof guessResultData[key] === 'object' 
                                        ? JSON.stringify(guessResultData[key]) 
                                        : parseResult(guessResultData[key])}
                            </div>
                            <div className='guess-element-guess-result-value'>
                                    
                        </div>
                        </div>
                    ))}
                </div>
                <p>{guessResult}</p>
        </div>
        </>
    );
}
export default GuessComponent;