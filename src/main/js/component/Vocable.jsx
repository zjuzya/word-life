import React from 'react';

const ReactDOM = require('react-dom');

export class Vocable extends React.Component {

    constructor(props) {
        super(props);
    }

    render() {
        const vocable = this.props.vocable;
        const description = this.props.description;
        const points = this.props.points;
        const blockDescription = this.props.blockDescription;
        const showPoints = this.props.showPoints;
        return <tr>{vocable && <td>{vocable}</td>}{!blockDescription && description && <td>{description}</td>}
            {showPoints && points && <td style={{textAlign: "center"}}>{points}</td>}</tr>
    }
}

Vocable.defaultProps = {
    blockDescription: true,
    showPoints: false,
};