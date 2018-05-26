/* eslint-env jest */
import React from 'react'
import { Root } from './Root'
import { shallow } from 'enzyme'
import { MuiThemeProvider } from '@material-ui/core/styles'
import CssBaseline from '@material-ui/core/CssBaseline'
import ErrorBoundary from './components/ErrorBoundary'
import { Provider } from 'react-redux'
import { BrowserRouter } from 'react-router-dom'

it('renders without crashing', () => {
  const rootWrapper = shallow(<Root classes={{root: {}}} />)
  expect(rootWrapper.find(MuiThemeProvider)).toHaveLength(1)
  expect(rootWrapper.find(CssBaseline)).toHaveLength(1)
  expect(rootWrapper.find(ErrorBoundary)).toHaveLength(1)
  expect(rootWrapper.find(Provider)).toHaveLength(1)
  expect(rootWrapper.find(BrowserRouter)).toHaveLength(1)
})
