import React from 'react';
import { Link } from 'react-router-dom';

function Menu() {
  return (
    <nav id="menu">
      <hr/>
      <p>Menu</p>
      <ul>
        <li><Link to="/">Hobbies</Link></li>
        <li><Link to="/helloworld">Hello World</Link></li>
      </ul>
    </nav>
  );
}

export default Menu;