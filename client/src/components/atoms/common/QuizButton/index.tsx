import React from 'react';
import { QuizButtonWrapper } from './style';

interface IQuizButtonProps {
	text: string;
	handleClick: () => void;
	color: 'Normal' | 'SubFirst';
	disabled?: boolean;
}

function QuizButton(props: IQuizButtonProps) {
	const { disabled, color, text, handleClick } = props;
	return (
		<QuizButtonWrapper disabled={disabled ?? false} $color={color} onClick={handleClick}>
			{text}
		</QuizButtonWrapper>
	);
}

export default QuizButton;
