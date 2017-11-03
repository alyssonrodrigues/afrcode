import React, {Component} from 'react';

class FotoAtualizacoes extends Component {
    constructor(props) {
        super(props);
        this.state = {likeada: this.props.foto.likeada};
    }

    like(event) {
        event.preventDefault();

        fetch(`http://localhost:8080/api/fotos/${this.props.foto.id}/like?X-AUTH-TOKEN=${localStorage.getItem('auth-token')}`,
            {method: 'POST'})
            .then(response => {
                if (response.ok) {
                    return response.json();
                } else {
                    throw new Error("não foi possível realizar o like da foto");
                }
            })
            .then(like => {
                this.setState({likeada : !this.state.likeada})
            });
    }

    render() {
        return (
            <section className="fotoAtualizacoes">
                <a onClick={this.like.bind(this)}
                   className={this.state.likeada ? 'fotoAtualizacoes-like-ativo' : 'fotoAtualizacoes-like'}>
                    Linkar
                </a>
                <form className="fotoAtualizacoes-form">
                    <input type="text" placeholder="Adicione um comentário..."
                           className="fotoAtualizacoes-form-campo"/>
                    <input type="submit" value="Comentar!" className="fotoAtualizacoes-form-submit"/>
                </form>

            </section>
        );
    }
}

class FotoInfo extends Component {
    render() {
        return (
            <div className="foto-in fo">
                <div className="foto-info-likes">

                    {
                        this.props.foto.likers.map(liker => (
                            <a href={`/timeline/${liker.login}`}>
                                {liker.login},
                            </a>
                        ))
                    }

                    curtiram

                </div>

                <p className="foto-info-legenda">
                    <a className="foto-info-autor" href={`/timeline/${this.props.foto.loginUsuario}`}>
                        {this.props.foto.loginUsuario}
                    </a>
                    {
                        ` ${this.props.foto.comentario}`
                    }
                </p>

                <ul className="foto-info-comentarios">
                    {
                        this.props.foto.comentarios.map(comentario => (
                            <li className="comentario" key={comentario.id}>
                                <a className="foto-info-autor" href={`/timeline/${comentario.login}`}>
                                    {comentario.login}
                                </a>
                                {comentario.texto}
                            </li>
                        ))
                    }
                </ul>
            </div>
        );
    }
}

class FotoHeader extends Component {
    render() {
        return (
            <header className="foto-header">
                <figure className="foto-usuario">
                    <img src={this.props.foto.urlPerfil} alt="foto do usuario"/>
                    <figcaption className="foto-usuario">
                        <a href={`/timeline/${this.props.foto.loginUsuario}`}>
                            {this.props.foto.loginUsuario}
                        </a>
                    </figcaption>
                </figure>
                <time className="foto-data">{this.props.foto.horario}</time>
            </header>
        );
    }
}

export default class Foto extends Component {
    render() {
        return (
            <div className="foto">
                <FotoHeader foto={this.props.foto}/>
                <img alt="foto" className="foto-src"
                     src={this.props.foto.urlFoto}/>
                <FotoInfo foto={this.props.foto}/>
                <FotoAtualizacoes foto={this.props.foto}/>
            </div>
        );
    }
}