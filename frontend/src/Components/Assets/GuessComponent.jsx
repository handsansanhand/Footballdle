import './GuessComponent.css'
import { parseString } from "../Services/parseString";
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
    return (
        <>
        <div className='guess-container' style={{ marginTop: "20px" }}>
            <h3>Guess Result:</h3>
                <div className='guess-grid'>
                    {Object.entries(parsedGuess).map( ([key,value]) => (
                        <div key={key} className='guess-element'> 
                            <div className='guess-element-title'>{parseString(key)}</div>
                             <div>{typeof value === 'object' ? JSON.stringify(value) : value}</div>
                        </div>
                    ))}
                </div>
                <p>{guessResult}</p>
        </div>
        </>
    );
}
export default GuessComponent;