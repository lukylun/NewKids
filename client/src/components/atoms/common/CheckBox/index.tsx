import React from 'react';
import { CheckBoxWrapper } from './style';

interface ICheckBoxProps {
	text: string;
}

function CheckBox(props: ICheckBoxProps) {
	const { text } = props;
	return (
		<CheckBoxWrapper>
			<input type="checkbox" id="input" />
			<label htmlFor="input">{text}</label>
		</CheckBoxWrapper>
	);
}

export default CheckBox;
