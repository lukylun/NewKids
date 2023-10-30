import React from 'react';
import { QuestionWrapper } from './style';

interface IQuestionProps {
	text: string;
}

function Question({ text }: IQuestionProps) {
	return (
		<QuestionWrapper>
			<div className="question-content" dangerouslySetInnerHTML={{ __html: text }} />
		</QuestionWrapper>
	);
}

export default Question;
