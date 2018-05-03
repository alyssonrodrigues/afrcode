import React from 'react'
import { Provider } from 'react-redux'
import { BrowserRouter } from 'react-router-dom'
import CssBaseline from 'material-ui/CssBaseline'
import { MuiThemeProvider, createMuiTheme, withStyles } from 'material-ui/styles'
import blue from 'material-ui/colors/blue'

import ErrorBoundary from './components/ErrorBoundary'
import App from './components/App'
import configureStore from './store/configureStore'

const store = configureStore()

const theme = createMuiTheme({
  palette: {
    primary: blue
  }
})

const styles = theme => ({
  root: theme.typography.body1
})

const Root = (props) => (
  <MuiThemeProvider theme={theme}>
    <div className={props.classes.root}>
      <CssBaseline />
      <ErrorBoundary>
        <Provider store={store}>
          <BrowserRouter>
            <App />
          </BrowserRouter>
        </Provider>
      </ErrorBoundary>
    </div>
  </MuiThemeProvider>
)

export default withStyles(styles)(Root)
