import React from 'react';
import { BrowserRouter, Route, Switch } from 'react-router-dom';

import AuthenticatedRoute from './AuthenticatedRoute';
import Login from '../components/Login';
import Logout from '../components/Login';
import AppIndex from '../components/AppIndex';
import NotFoundPage from '../components/NotFoundPage';

const AppRoutes = props => (
  <BrowserRouter>
    <Switch>
      {/* Defina as rotas por ordem de especificidade, das mais específicas para as mais genéricas... */}
      <Route path='/login' component={Login} />
      <Route path='/logout' component={Logout} />
      <AuthenticatedRoute exact path='/' component={AppIndex} isUserAuthenticated={props.isUserAuthenticated} />
      <Route component={NotFoundPage} />
    </Switch>
  </BrowserRouter>
);

export default AppRoutes;