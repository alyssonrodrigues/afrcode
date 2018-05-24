/* eslint-env jest */
import React from 'react'
import Root from './Root'
import { shallow } from 'enzyme'
import { MuiThemeProvider } from 'material-ui/styles'
import CssBaseline from 'material-ui/CssBaseline'
import ErrorBoundary from './components/ErrorBoundary'
import { Provider } from 'react-redux'
import { BrowserRouter } from 'react-router-dom'

it('renders without crashing', () => {
  const rootWrapper = shallow(<Root />)
  expect(rootWrapper.find(MuiThemeProvider))
  expect(rootWrapper.find(CssBaseline))
  expect(rootWrapper.find(ErrorBoundary))
  expect(rootWrapper.find(Provider))
  expect(rootWrapper.find(BrowserRouter))
})
