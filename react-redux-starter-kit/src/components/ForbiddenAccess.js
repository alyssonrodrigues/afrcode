import React from 'react'
import { Link } from 'react-router-dom'

import App from './App'

// TODO refinar página de acesso negado
const ForbiddenAccess = () => (
  <App noAppToolbar>
    <h4>
      403 Acesso negado
    </h4>
    <Link to='/'>Retornar</Link>
  </App>
)

export default ForbiddenAccess
