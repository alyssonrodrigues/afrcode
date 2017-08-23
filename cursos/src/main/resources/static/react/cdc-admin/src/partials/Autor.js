import React, { Component } from "react";
import $ from "jquery";
import CustomInput from "../components/CustomInput";
import PubSub from "pubsub-js";

class AutorInput extends Component {
    constructor() {
        super();
        this.state = {
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
        this.setState({ nome: event.target.value });
    }

    setEmail(event) {
        this.setState({ email: event.target.value });
    }

    setSenha(event) {
        this.setState({ senha: event.target.value });
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
            success: result => PubSub.publish("autorsListUpdated", result),
            error: error => console.log(error)
        });
    }

    render() {
        return (
            <div className="pure-form pure-form-aligned">
                <form className="pure-form pure-form-aligned"
                    onSubmit={this.submitForm} method="post">
                    <CustomInput id="nome" type="text" name="nome" label="Nome"
                        value={this.state.nome} onChange={this.setNome} />
                    <CustomInput id="email" type="email" name="email" label="Email"
                        value={this.state.email} onChange={this.setEmail} />
                    <CustomInput id="senha" type="password" name="senha" label="Senha"
                        value={this.state.senha} onChange={this.setSenha} />
                    <div className="pure-control-group">
                        <label></label>
                        <button type="submit" className="pure-button pure-button-primary">
                            Gravar
                        </button>
                    </div>
                </form>
            </div>
        );
    }
}

class AutorsList extends Component {
    render() {
        return (
            <div>
            <table className="pure-table">
              <thead>
                <tr>
                  <th>Nome</th>
                  <th>Email</th>
                </tr>
              </thead>
              <tbody>
                {
                  this.props.autorsList.map(autor => 
                    <tr key={autor.id}><td>{autor.nome}</td><td>{autor.email}</td></tr>)
                }
              </tbody>
            </table>
          </div>
        );
    }
}

export default class AutorBox extends Component {
    constructor() {
        super();
        this.state = {
            autors: []
        };
    }

    componentDidMount() {
        $.ajax({
            url: "http://localhost:8080/api/autores",
            dataType: "json",
            success: result => this.setState({ autors: result })
        });
        PubSub.subscribe("autorsListUpdated", 
            (topic, object) => this.setState({ autors: object }));
    }

    render() {
        return (
            <div>
                <AutorInput />
                <AutorsList autorsList={this.state.autors} />
            </div>
        );
    }
}