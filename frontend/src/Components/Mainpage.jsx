import Button from 'react-bootstrap/Button';
import LeageButtons from './Assets/LeagueButtons';
import SearchBar from './Assets/SearchBar';
import { useState } from 'react';
import './Mainpage.css'
import GuessComponent from './Assets/GuessComponent';
//main page of the frontend
function Mainpage() {
     const [selectedLeague, setSelectedLeague] = useState("premier_league_players_table");
    // callback to receive guess result from SearchBar
  const handleGuessMade = (resultText) => {
    setGuessResult(resultText);
  };

  const [guessResult, setGuessResult] = useState("");  // state for guess text

  
     return (
    <>
    <div className='main-container'>
    <LeageButtons onLeagueChange={setSelectedLeague}></LeageButtons>
      <SearchBar leagueName={selectedLeague} onGuessMade={handleGuessMade}></SearchBar>
    <GuessComponent guessResult={guessResult}></GuessComponent>
    </div>
     
    </>
  );
}
export default Mainpage;