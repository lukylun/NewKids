import React, { useState } from 'react';
import { ReactComponent as DownIcon } from 'assets/icons/down.svg';
import { ReactComponent as Logo } from 'assets/imgs/newkids-logo.svg';
import SearchBar from 'components/atoms/common/SearchBar';
import Button from 'components/atoms/common/Button';
import useMovePage from 'hooks/useMovePage';
import { useRecoilState } from 'recoil';
import { MemberInfoState } from 'store/auth';
import { Link } from 'react-router-dom';
import { NavBarContainer } from './style';

export function AuthNavBar() {
	const [movePage] = useMovePage();

	return (
		<NavBarContainer $auth>
			<div className="logo auth-navbar">
				<Logo onClick={() => movePage('/')} />
			</div>
		</NavBarContainer>
	);
}

function NavBar() {
	const [memberInfoState, setMemberInfoState] = useRecoilState(MemberInfoState);
	const [movePage] = useMovePage();
	const [searchValue, setSearchValue] = useState('');

	const logout = () => {
		if (window.confirm('정말 로그아웃 하시겠어요?')) {
			localStorage.removeItem('token');
			localStorage.removeItem('memberkey');
			setMemberInfoState(null);
			window.location.href = '/';
		}
	};

	const confirmSearch = () => {
		movePage(`/article?search=${searchValue}`);
		const searchEvent = new Event('article-search');
		window.dispatchEvent(searchEvent);
	};

	return (
		<NavBarContainer>
			<div className="logo">
				<Logo onClick={() => movePage('/')} />
			</div>
			<div className="search-bar">
				<SearchBar
					size="l"
					confirmSearch={confirmSearch}
					value={searchValue}
					setValue={setSearchValue}
					color="SubFirst"
					placeholder="검색어를 입력하세요"
				/>
			</div>
			{/* 로그인 여부에 따라 다르게 표시 */}
			{memberInfoState ? (
				<div className="member-info">
					<span>{memberInfoState.nickname}님</span> <DownIcon />
					<div className="menu">
						<Link to="/mypage/info">마이페이지</Link>
						<button onClick={logout} type="button">
							로그아웃
						</button>
					</div>
				</div>
			) : (
				<Button size="s" radius="l" color="Primary" text="로그인" handleClick={() => movePage('/auth/login')} />
			)}
		</NavBarContainer>
	);
}

export default NavBar;
