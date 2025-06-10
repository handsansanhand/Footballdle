import './LeagueButtons.css'
import Button from 'react-bootstrap/Button';
//component which contains the main league buttons
function LeageButtons( {onLeagueChange} ) {
    return (
        <>
        <div className="button-list-container">
                  <Button variant="outline-primary" onClick={() => onLeagueChange("all_leagues_players_table")}>
        All Leagues
      </Button>
      <Button variant="outline-primary" onClick={() => onLeagueChange("premier_league_players_table")}>
        Premier League
      </Button>
      <Button variant="outline-primary" onClick={() => onLeagueChange("la_liga_players_table")}>
        La Liga
      </Button>
      <Button variant="outline-primary" onClick={() => onLeagueChange("ligue_1_players_table")}>
        Ligue 1
      </Button>
      <Button variant="outline-primary" onClick={() => onLeagueChange("bundesliga_players_table")}>
        Bundesliga
      </Button>
      <Button variant="outline-primary" onClick={() => onLeagueChange("serie_a_players_table")}>
        Serie A
      </Button>
        </div>
         </>
    );
}

export default LeageButtons;