import React from 'react';
import { Link } from 'react-router-dom';

const NotFoundPage = () => {
  // TODO refinar página não encontrada
  return (
    <div>
      <h4>
        404 Página não encontrada
      </h4>
      <Link to="/">Retornar</Link>
    </div>
  );
};

export default NotFoundPage;
