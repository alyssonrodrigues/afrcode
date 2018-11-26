import React from 'react'
import { mount } from 'enzyme'
import { Provider } from 'react-redux'
import { MemoryRouter } from 'react-router-dom'
import Card from '@material-ui/core/Card'
import { Field } from 'redux-form'
import { MuiThemeProvider } from '@material-ui/core'

import Login from './Login'
import { theme } from '../RootStyles'

describe('<Login />', () => {
  it('Login snapshot deveria ocorrer sem falhas!', () => {
    const store = mockReduxStore({
      authentication: { isUserAuthenticated: false },
      operationProgress: { operationInProgress: false },
      messages: {}
    })
    const tree = mount(
      <MuiThemeProvider theme={theme}>
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
