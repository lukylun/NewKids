import styled, { css } from 'styled-components';
import { ButtonColorStyles } from 'styles/styles';

interface IQuizButtonWrapperProps {
	$color: 'Normal' | 'Primary' | 'Danger' | 'Success' | 'SubFirst' | 'SubSecond';
	disabled: boolean;
}

export const QuizButtonWrapper = styled.button<IQuizButtonWrapperProps>`
	width: 150px;
	height: 150px;
	margin: 20px;
	padding: 20px;
	border: 1px solid black;
	border-radius: 10px;
	font-size: 1.5rem;

	${({ $color }) => ButtonColorStyles[$color]}
	${({ disabled }) =>
		disabled
			? css`
					background-color: var(--gray-300);
					color: var(--gray-500);
			  `
			: css``}
`;
