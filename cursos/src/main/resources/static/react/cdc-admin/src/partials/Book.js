import React, { Component } from "react";
import $ from "jquery";
import CustomInput from "../components/CustomInput";
import CustomMessages from "../components/CustomMessages";
import PubSub from "pubsub-js";

class BookInput extends Component {
    constructor() {
        super();
        this.state = {
            titulo: "",
            preco: "",
            autorId: ""
        };
        this.submitForm = this.submitForm.bind(this);
        this.setTitulo = this.setTitulo.bind(this);
        this.setPreco = this.setPreco.bind(this);
        this.setAutorId = this.setAutorId.bind(this);
    }

    setTitulo(event) {
        this.setState({ titulo: event.target.value });
    }

    setPreco(event) {
        this.setState({ preco: event.target.value });
    }

    setAutorId(event) {
        this.setState({ autorId: event.target.value });
    }

    submitForm(event) {
        event.preventDefault();
        $.ajax({
            url: "http://localhost:8080/api/livros",
            type: "post",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify({
                titulo: this.state.titulo,
                preco: this.state.preco,
                autorId: this.state.autorId
            }),
            success: result => {
                PubSub.publish("booksListUpdated", result);
                PubSub.publish("messages", [
                    {
                        key: "book", 
                        textContent: "Livro cadastrado com sucesso!"
                    }
                ]);
                this.setState({
                    titulo: "",
                    preco: "",
                    autorId: ""
                });
            },
            error: error => PubSub.publish("messages", 
                error.responseJSON.errors.map(message => {
                    return {key: message.field, textContent: message.defaultMessage};
                })
            )
        });
    }

    render() {
        return (
            <div className="pure-form pure-form-aligned">
                <form className="pure-form pure-form-aligned"
                    onSubmit={this.submitForm} method="post">
                    <CustomInput id="titulo" type="text" name="titulo" label="Título"
                        value={this.state.titulo} onChange={this.setTitulo} />
                    <CustomInput id="preco" type="number" name="preco" label="Preço"
                        min="0.01" step="0.01"
                        value={this.state.preco} onChange={this.setPreco} />
                    <div className="pure-control-group">
                        <label htmlFor="autorId">Autor</label>
                        <select id="autorId" name="autorId"
                            value={this.state.autorId} onChange={this.setAutorId}>
                            <option value="">Selecione...</option>
                            {
                                this.props.autorsList.map(autor =>
                                    <option value={autor.id}>{autor.nome}</option>)
                            }
                        </select>
                    </div>
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

class BooksList extends Component {
    render() {
        return (
            <div>
            <table className="pure-table">
              <thead>
                <tr>
                  <th>Titulo</th>
                  <th>Preço</th>
                  <th>Autor</th>
                </tr>
              </thead>
              <tbody>
                {
                  this.props.booksList.map(book => 
                    (<tr key={book.id}>
                        <td>{book.titulo}</td>
                        <td>{book.preco}</td>
                        <td>{book.autor.nome}</td>
                    </tr>))
                }
              </tbody>
            </table>
          </div>
        );
    }
}

export default class BookBox extends Component {
    constructor() {
        super();
        this.state = {
            books: [],
            autors: []
        };
    }

    componentDidMount() {
        $.ajax({
            url: "http://localhost:8080/api/livros",
            dataType: "json",
            success: result => this.setState({ books: result })
        });
        $.ajax({
            url: "http://localhost:8080/api/autores",
            dataType: "json",
            success: result => this.setState({ autors: result })
        });
        PubSub.subscribe("booksListUpdated", 
            (topic, object) => this.setState({ books: object }));
    }

    render() {
        return (
            <div id="main">
                <div className="header">
                <h1>Livros</h1>
                </div>
                <CustomMessages />
                <div className="content" id="content">
                    <BookInput autorsList={this.state.autors} />
                    <BooksList booksList={this.state.books} />
                </div>
            </div>
        );
    }
}