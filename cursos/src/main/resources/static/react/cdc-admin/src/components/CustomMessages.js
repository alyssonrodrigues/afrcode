import React, { Component } from "react";
import PubSub from "pubsub-js";

export default class CustomMessages extends Component {
    constructor() {
        super();
        this.state = {
            messages: []
        };
    }

    componentDidMount() {
        PubSub.subscribe("messages", 
            (topic, obj) => {
                console.log(obj);
                this.setState({ messages: obj })
            }
        );
    }

    render() {
        return (
            <span className="error">{
                this.state.messages.map(message => <p key={message.field}>{message.field} {message.defaultMessage}</p>)
            }
            </span>
        );
    }
}