import React from 'react';
import { Link } from 'react-router-dom';

// TODO refinar página não encontrada
const NotFoundPage = () => (
  <div>
    <h4>
      404 Página não encontrada
    </h4>
    <Link to="/">Retornar</Link>
  </div>
);


export default NotFoundPage;
