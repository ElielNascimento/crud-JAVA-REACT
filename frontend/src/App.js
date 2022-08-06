import "./components/styles.css";
import NavBar from "./components/NavBar";
import Filme from "./components/Filme";
import "bootstrap/dist/css/bootstrap.min.css";

function App() {
  return (
    <div className="container">
      <h1>APLICAÇÃO REACT + JAVA + SPRINGBOOT</h1>
      <NavBar/>
      <Filme />
     
    </div>
  );
}
export default App;
