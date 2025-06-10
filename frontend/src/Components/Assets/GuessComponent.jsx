
function GuessComponent( {guessResult} ) {
    return (
        <>
          <div style={{ marginTop: "20px" }}>
      <h3>Guess Result:</h3>
      <p>{guessResult || "Make a guess to see the result here."}</p>
    </div>
        </>
    );
}
export default GuessComponent;