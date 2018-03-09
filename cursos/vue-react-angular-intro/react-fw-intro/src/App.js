import React, { Component } from 'react';
import { BrowserRouter, Switch, Route} from 'react-router-dom';

import Menu from './components/Menu';
import Hobbies from './components/Hobbies';
import HelloWorld from './components/HelloWorld';

class App extends Component {
  render() {
    return (
      <BrowserRouter>
        <div id="app">
          <Switch>
            <Route exact path="/" component={Hobbies} />
            <Route path="/helloworld" component={HelloWorld} />
          </Switch>
          <Menu/>
        </div>
      </BrowserRouter>
    );
  }
}

export default App;