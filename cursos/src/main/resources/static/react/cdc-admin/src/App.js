import React, { Component } from "react";
import $ from "jquery";
import CustomInput from "./components/custom-input";
import "./css/pure-min.css"
import "./css/side-menu.css";

class App extends Component {
  constructor() {
    super();
    this.state = { 
      autores: [],
      nome: "",
      email: "",
      senha: ""
    };
    this.submitForm = this.submitForm.bind(this);
    this.setNome = this.setNome.bind(this);
    this.setEmail = this.setEmail.bind(this);
    this.setSenha = this.setSenha.bind(this);
  }

  setNome(event) {
    this.setState({nome: event.target.value});
  }

  setEmail(event) {
    this.setState({email: event.target.value});
  }

  setSenha(event) {
    this.setState({senha: event.target.value});
  }

  submitForm(event) {
    event.preventDefault();
    $.ajax({
      url: "http://localhost:8080/api/autores",
      type: "post",
      contentType: "application/json",
      dataType: "json",
      data: JSON.stringify({
        nome: this.state.nome, 
        email: this.state.email, 
        senha: this.state.senha
      }),
      success: result => this.setState({autores: result}),
      error: error => console.log(error)
    });
  }

  componentDidMount() {
    $.ajax({
      url: "http://localhost:8080/api/autores",
      dataType: "json",
      success: result => this.setState({autores : result})
    });
  }

  render() {
    return (
      <div id="layout">
        <a href="#menu" id="menuLink" className="menu-link">
          <span></span>
        </a>

        <div id="menu">
          <div className="pure-menu">
            <a className="pure-menu-heading" href="/">Company</a>

            <ul className="pure-menu-list">
              <li className="pure-menu-item"><a href="/" className="pure-menu-link">Home</a></li>
              <li className="pure-menu-item"><a href="/" className="pure-menu-link">Autores</a></li>
              <li className="pure-menu-item"><a href="/" className="pure-menu-link">Livros</a></li>
            </ul>
          </div>
        </div>

        <div id="main">
          <div className="header">
            <h1>Autores</h1>
          </div>
          <div className="content" id="content">
            <div className="pure-form pure-form-aligned">
              <form className="pure-form pure-form-aligned" 
                onSubmit={this.submitForm} method="post">
                <CustomInput id="nome" type="text" name="nome" label="Nome"
                  value={this.state.nome} onChange={this.setNome} />
                <CustomInput id="email" type="email" name="email" 
                  value={this.state.email} onChange={this.setEmail} />
                <CustomInput id="senha" type="password" name="senha" 
                  value={this.state.senha} onChange={this.setSenha} />
                <div className="pure-control-group">
                  <label></label>
                  <button type="submit" className="pure-button pure-button-primary">Gravar</button>
                </div>
              </form>

            </div>
            <div>
              <table className="pure-table">
                <thead>
                  <tr>
                    <th>Nome</th>
                    <th>email</th>
                  </tr>
                </thead>
                <tbody>
                  {
                    this.state.autores.map(autor => 
                      <tr key={autor.id}><td>{autor.nome}</td><td>{autor.email}</td></tr>)
                  }
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default App;
