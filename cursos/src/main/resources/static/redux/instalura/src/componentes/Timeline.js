import React, {Component} from 'react';
import Foto from './Foto';
import {withRouter} from 'react-router-dom';
import Pubsub from 'pubsub-js';

export class Timeline extends Component {
    constructor(props) {
        super(props);
        this.state = {fotos: []};
        this.login = this.props.login;
    }

    componentWillMount() {
        Pubsub.subscribe('timeline', (topico, fotos) => {
            console.log(fotos);
            this.setState({fotos: fotos.fotos});
        });
        Pubsub.subscribe('atualiza-liker', (topico, infoLiker) => {
            const fotoAchada = this.state.fotos.find(foto => foto.id === infoLiker.fotoId);
            fotoAchada.likeada = !fotoAchada.likeada;
            const possivelLiker = fotoAchada.likers.find(liker => liker.login === infoLiker.liker.login);
            if (possivelLiker === undefined) {
                fotoAchada.likers.push(infoLiker.liker);
            } else {
                const novosLikers = fotoAchada.likers.filter(liker => liker.login !== infoLiker.liker.login);
                fotoAchada.likers = novosLikers;
            }
            this.setState({fotos: this.state.fotos});
        });
        Pubsub.subscribe('novos-comentarios', (topico, infoComentario) => {
            const fotoAchada = this.state.fotos.find(foto => foto.id === infoComentario.fotoId);
            fotoAchada.comentarios.push(infoComentario.novoComentario);
            this.setState({fotos: this.state.fotos});
        });
    }

    componentDidMount() {
        if (localStorage.getItem('auth-token') === null) {
            this.props.history.push('/?msg=É necessário fazer o login...');
            return;
        }

        let url = `http://localhost:8080/api/fotos?X-AUTH-TOKEN=${localStorage.getItem('auth-token')}`;
        if (this.props.match.params.user) {
            url = `http://localhost:8080/api/public/fotos/${this.props.match.params.user}`;
        }
        fetch(url)
            .then(response => response.json())
            .then(fotos => {
                this.setState({fotos: fotos});
            });
    }

    like(fotoId) {
        fetch(`http://localhost:8080/api/fotos/${fotoId}/like?X-AUTH-TOKEN=${localStorage.getItem('auth-token')}`,
            {method: 'POST'})
            .then(response => {
                if (response.ok) {
                    return response.json();
                } else {
                    throw new Error("não foi possível realizar o like da foto");
                }
            })
            .then(liker => {
                Pubsub.publish('atualiza-liker',
                    {
                        fotoId: fotoId,
                        liker: liker
                    }
                );
            });
    }

    comenta(fotoId, comentario) {
        const requestInfo = {
            method: 'POST',
            body: JSON.stringify({texto: comentario}),
            headers: new Headers({
                'Content-type': 'application/json'
            })
        };

        fetch(`http://localhost:8080/api/fotos/${fotoId}/comment?X-AUTH-TOKEN=${localStorage.getItem('auth-token')}`,
            requestInfo)
            .then(response => {
                if (response.ok) {
                    return response.json();
                } else {
                    throw new Error("não foi possível comentar");
                }
            })
            .then(novoComentario => {
                Pubsub.publish('novos-comentarios',
                    {
                        fotoId: fotoId,
                        novoComentario: novoComentario
                    });
            });
    }
    render() {
        return (
            <div className="fotos container">
                {
                    this.state.fotos.map(foto =>
                        <Foto key={foto.id} foto={foto} like={this.like} comenta={this.comenta}/>
                    )
                }
            </div>
        );
    }
}

export default withRouter(Timeline);