/* eslint-env jest */
import React from 'react'
import ReactDOM from 'react-dom'
import Root from './Root'

describe('<Root />', () => {
  it('Root render deveria ocorrer sem falhas!', () => {
    const div = document.createElement('div')
    ReactDOM.render(<Root />, div)
    ReactDOM.unmountComponentAtNode(div)
  })
})
