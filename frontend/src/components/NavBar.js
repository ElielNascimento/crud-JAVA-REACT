import "./styles.css";

import Filme from "./Filme";

import styles from './styles/navbar.module.css'

function NavBar() {
  return (
   
     
       
        <header>
    <nav className={styles.navbar}>
        <h4> DESENVOLVEDOR. Eliel Nascimento </h4>
        <ul className={styles.nav_menu}>
            <li className="nav_item"><a href="!#" className={styles.nav_link}>Home</a></li>
            <li className="nav_item"><a href="!#" className={styles.nav_link}>Sobre</a></li>
            <li className="nav_item"><a href="!#" className={styles.nav_link}>Projetos</a></li>
            <li className="nav_item"><a href="!#" className={styles.nav_link}>Tecnologias</a></li>
            <li className="nav_item"><a href="!#" className={styles.nav_link}>Contato</a></li>
        </ul>
    </nav>          
  </header>
       
      
    
  );
}

export default NavBar;
