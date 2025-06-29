import './GuessComponent.css'
import { parseString } from "../Services/parseString";
import { parseResult } from '../Services/parseResult';
import { ImCross } from "react-icons/im";

function GuessComponent( {guessResult} ) {
    if(!guessResult) {
        return (
             <>
          <div className='guess-container' style={{ marginTop: "20px" }}>
      <p>{"Make a guess to see the result here."}</p>
    </div>
        </>
        )
    }
    const parsedGuess = guessResult ? JSON.parse(guessResult) : null;
    const guessedPlayer = parsedGuess ? parsedGuess.guessed_player : null;
    const guessResultData = parsedGuess ? parsedGuess.guess_result : null;
     return (
    <div className='guess-container' style={{ marginTop: "20px" }}>
      <h3>{guessedPlayer.player}</h3>
      <div className='guess-grid'>
        {Object.entries(guessedPlayer)
          .filter(([key]) => key !== 'id' && key !== 'player')
          .map(([key, value]) => {
            //if i see a position field, split it up, and for each entry of the positions, determine if its right or not
            if (key === 'position' && typeof guessResultData.position === 'object') {
              //split comma-separated positions and map over each
              const positions = value.split(',').map(pos => pos.trim());

              return (
                <div key={key} className='guess-element'>
                  <div className='guess-element-title'>{parseString(key)}</div>
                   <div className='guess-element-answer' >
                    <div className='guess-element-guessed-value'>
                    {positions.map(pos => (
                      <div key={pos} className='position-row'>
                        <span>{pos}</span>
                        <span>{parseResult(guessResultData.position[pos])}</span>
                      </div>
                    ))}
                  </div>
                    
                     </div>
                  
                </div>
              );
            }

            //default case for other fields
            return (
  <div key={key} className='guess-element'>
    <div className='guess-element-title'>{parseString(key)}</div>
    <div className='guess-element-value-box'>
      {typeof value === 'object' ? JSON.stringify(value) : value}
    </div>
    <div className='guess-element-result'>
      {typeof guessResultData[key] === 'object'
        ? JSON.stringify(guessResultData[key])
        : parseResult(guessResultData[key])}
    </div>
  </div>
);
          })}
      </div>
    </div>
  );
}
export default GuessComponent;