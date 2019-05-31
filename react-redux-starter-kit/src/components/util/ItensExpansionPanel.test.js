import React from 'react'
import { mount } from 'enzyme'

import ItensExpansionPanel from './ItensExpansionPanel'

describe('ItensExpansionPanel', () => {
  // Rendering tests...
  it('Render dos componentes principais deveria ocorrer se falhas!', () => {
    const wrapper = mount((
      <ItensExpansionPanel
        classes={{}}
        numItensVisiveis={1}
        itensComponents={[].concat(<div id='itemVisivel' key='itemVisivel' />, <div id='itemExpansivel' key='itemExpansivel' />)} />
    ))
    expect(wrapper.find('ExpansionPanel')).toHaveLength(1)
    expect(wrapper.find('ExpansionPanelSummary')).toHaveLength(1)
    expect(wrapper.find('div[id="itemVisivel"]')).toHaveLength(1)
    expect(wrapper.find('IconButton')).toHaveLength(1)
    expect(wrapper.find('Badge')).toHaveLength(1)
    expect(wrapper.find('ExpansionPanelDetails')).toHaveLength(1)
    expect(wrapper.find('div[id="itemExpansivel"]')).toHaveLength(1)
  })

  it('Render null deveria ocorrer quando não informados numItensVisiveis ou itensComponents!', () => {
    const wrapper = mount((
      <ItensExpansionPanel
        classes={{}} />
    ))
    expect(wrapper.props().children).toBe(undefined)
    expect(wrapper.find('ExpansionPanel')).toHaveLength(0)
  })

  it('TablePagination render deveria ocorrer quando informado expandedRowsPerPage!', () => {
    const wrapper = mount((
      <ItensExpansionPanel
        classes={{}}
        numItensVisiveis={1}
        expandedRowsPerPage={2}
        itensComponents={[].concat(
          <div id='itemVisivel' key='itemVisivel' />,
          <div id='itemExpansivel1' key='itemExpansivel1' />,
          <div id='itemExpansivel2' key='itemExpansivel2' />,
          <div id='itemExpansivel3' key='itemExpansivel3' />)} />
    ))
    expect(wrapper.find('div[id="itemVisivel"]')).toHaveLength(1)
    expect(wrapper.find('TablePagination')).toHaveLength(1)
    expect(wrapper.find('div[id="itemExpansivel1"]')).toHaveLength(1)
    expect(wrapper.find('div[id="itemExpansivel2"]')).toHaveLength(1)
    expect(wrapper.find('div[id="itemExpansivel3"]')).toHaveLength(0)
  })

  it('Snapshot deveria ser gerado sem falhas!', () => {
    const wrapper = mount((
      <ItensExpansionPanel
        classes={{}}
        numItensVisiveis={1}
        expandedRowsPerPage={2}
        itensComponents={[].concat(
          <div id='itemVisivel' key='itemVisivel' />,
          <div id='itemExpansivel1' key='itemExpansivel1' />,
          <div id='itemExpansivel2' key='itemExpansivel2' />,
          <div id='itemExpansivel3' key='itemExpansivel3' />)} />
    ))
    expect(wrapper).toMatchSnapshot()
  })

  // Interactions tests...
  it('ExpansionPanel onClick deveria ocorrer sem falhas!', () => {
    const wrapper = mount((
      <ItensExpansionPanel
        classes={{}}
        numItensVisiveis={1}
        itensComponents={[].concat(<div id='itemVisivel' key='itemVisivel' />, <div id='itemExpansivel' key='itemExpansivel' />)} />
    ))
    const expansionPanelSummary = wrapper.find('ExpansionPanelSummary')
    expect(expansionPanelSummary).toHaveLength(1)
    expansionPanelSummary.simulate('click')
    wrapper.update()
    expect(wrapper.find('ExpansionPanel').state('expanded')).toBeTruthy()
    expansionPanelSummary.simulate('click')
    wrapper.update()
    expect(wrapper.find('ExpansionPanel').state('expanded')).toBeFalsy()
  })

  it('Paginação deveria ocorrer sem falhas!', () => {
    const wrapper = mount((
      <ItensExpansionPanel
        classes={{}}
        numItensVisiveis={1}
        expandedRowsPerPage={2}
        itensComponents={[].concat(
          <div id='itemVisivel' key='itemVisivel' />,
          <div id='itemExpansivel1' key='itemExpansivel1' />,
          <div id='itemExpansivel2' key='itemExpansivel2' />,
          <div id='itemExpansivel3' key='itemExpansivel3' />)} />
    ))
    expect(wrapper.find('TablePagination')).toHaveLength(1)
    expect(wrapper.find('div[id="itemVisivel"]')).toHaveLength(1)
    expect(wrapper.find('div[id="itemExpansivel1"]')).toHaveLength(1)
    expect(wrapper.find('div[id="itemExpansivel2"]')).toHaveLength(1)
    expect(wrapper.find('div[id="itemExpansivel3"]')).toHaveLength(0)
    let iconButtons = wrapper.find('IconButton')
    expect(iconButtons).toHaveLength(3)
    const nextIconButton = iconButtons.at(2)
    nextIconButton.props().onClick()
    wrapper.update()
    expect(wrapper.find('TablePagination')).toHaveLength(1)
    expect(wrapper.find('div[id="itemVisivel"]')).toHaveLength(0)
    expect(wrapper.find('div[id="itemExpansivel1"]')).toHaveLength(0)
    expect(wrapper.find('div[id="itemExpansivel2"]')).toHaveLength(0)
    expect(wrapper.find('div[id="itemExpansivel3"]')).toHaveLength(1)
    iconButtons = wrapper.find('IconButton')
    expect(iconButtons).toHaveLength(3)
    const previousIconButton = iconButtons.at(1)
    previousIconButton.props().onClick()
    wrapper.update()
    expect(wrapper.find('TablePagination')).toHaveLength(1)
    expect(wrapper.find('div[id="itemVisivel"]')).toHaveLength(1)
    expect(wrapper.find('div[id="itemExpansivel1"]')).toHaveLength(1)
    expect(wrapper.find('div[id="itemExpansivel2"]')).toHaveLength(1)
    expect(wrapper.find('div[id="itemExpansivel3"]')).toHaveLength(0)
  })
})
