/* eslint-env jest */
import React from 'react'
import { mount, shallow } from 'enzyme'
import Logout, { Logout as LogoutUnitTest } from './Logout'
import { MemoryRouter, Redirect } from 'react-router-dom'

it('Action @authenticationJwtActions/USER_LOGOUT deveria ter ocorrido sem falhas!', () => {
  const store = mockReduxStore({})
  const logoutWrapper = mount(
    <MemoryRouter>
      <Logout store={store} />
    </MemoryRouter>
  )
  const redirect = logoutWrapper.find(Redirect)
  expect(redirect).toHaveLength(1)
  expect(redirect.prop('to')).toBe('/')
  expect(store.getActions()[0]['type']).toEqual('@authenticationJwtActions/USER_LOGOUT')
})

it('Action method logoutUser deveria ter ocorrido sem falhas!', () => {
  const mockCallback = jest.fn()

  const logoutWrapper = shallow(
    <LogoutUnitTest logoutUser={mockCallback} />
  )
  const redirect = logoutWrapper.find(Redirect)
  expect(redirect).toHaveLength(1)
  expect(redirect.prop('to')).toBe('/')
  expect(mockCallback.mock.calls.length).toBe(1)
})
