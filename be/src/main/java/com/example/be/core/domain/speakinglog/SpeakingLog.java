package com.example.be.core.domain.speakinglog;

import com.example.be.core.domain.BaseEntity;
import com.example.be.core.domain.member.Member;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Getter
@Entity
@Where(clause = "deleted = false")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE speaking_log SET deleted = true WHERE id = ?")
public class SpeakingLog extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "speaking_log_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id", nullable = false)
	private Member member;

	private String title;

	@Lob
	private String voiceRecord;

	@Lob
	private String voiceText;

	public SpeakingLog(Member member, String title, String voiceRecord, String voiceText) {
		this.member = member;
		this.title = title;
		this.voiceRecord = voiceRecord;
		this.voiceText = voiceText;
	}
}
