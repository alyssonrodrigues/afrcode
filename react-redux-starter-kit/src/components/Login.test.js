import React from 'react'
import { mount } from 'enzyme'
import { Provider } from 'react-redux'
import { MemoryRouter } from 'react-router-dom'
import Card from '@material-ui/core/Card'
import { Field } from 'react-final-form'
import { MuiThemeProvider } from '@material-ui/core'

import { configTheme } from '../RootStyles'
import Login from './Login'

describe('<Login />', () => {
  it('Login snapshot deveria ocorrer sem falhas!', () => {
    const store = mockReduxStore({
      authentication: { isUserAuthenticated: false },
      operationProgress: { operationInProgress: false },
      mainMenu: {},
      messages: {}
    })
    const tree = mount(
      <MuiThemeProvider theme={configTheme({})}>
        <Provider store={store}>
          <MemoryRouter>
            <Login location={{}} />
          </MemoryRouter>
        </Provider>
      </MuiThemeProvider>
    )
    expect(tree.find('form')).toHaveLength(1)
    expect(tree.find(Card)).toHaveLength(1)
    expect(tree.find(Field)).toHaveLength(2)
  })
})
