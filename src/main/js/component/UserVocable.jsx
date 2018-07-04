import React from 'react';

export class UserVocable extends React.Component {

    constructor(props) {
        super(props);
        this.handleDelete = this.handleDelete.bind(this);
    }

    render() {
        const vocable = this.props.vocable;
        const description = this.props.description;
        return <tr>{vocable&&<td>{vocable}</td>}{description && <td>{description}</td>}
            <td><input type={"button"} onClick={this.handleDelete} value={"update"} /></td>
            <td><input type={"button"} onClick={this.handleDelete} value={"delete"} /></td></tr>
    }

    handleDelete() {

    }
}