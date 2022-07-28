import NavBar from "./components/NavBar";
import Filme from "./components/Filme";
import "bootstrap/dist/css/bootstrap.min.css";

function App() {
  return (
    <div className="container">
      <h1>CRUD FULL STACK</h1>
      <Filme />
      <NavBar/>
    </div>
  );
}
export default App;
