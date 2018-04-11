import React from 'react'
import { Provider } from 'react-redux'
import CssBaseline from 'material-ui/CssBaseline'
import { withStyles } from 'material-ui/styles'

import ErrorBoundary from './components/ErrorBoundary'
import AppRoutes from './routes/AppRoutes'
import configureStore from './store/configureStore'

const store = configureStore()

const styles = theme => ({
  root: theme.typography.body1
})

const Root = (props) => (
  <div className={props.classes.root}>
    <CssBaseline />
    <ErrorBoundary>
      <Provider store={store}>
        <AppRoutes />
      </Provider>
    </ErrorBoundary>
  </div>
)

export default withStyles(styles)(Root)
