package com.example.questionnaire_back.service.ifs;

import com.example.questionnaire_back.vo.QueRequest;
import com.example.questionnaire_back.vo.QueResponse;

public interface QueService {

	// 新增問卷資訊(後台)+
	public QueResponse addProject(QueRequest queRequest);

	// 修改問卷資訊(後台)+
	public QueResponse renewProject(QueRequest queRequest);

	// 刪除問卷資訊(後台)+
	public QueResponse deleteProject(QueRequest queRequest);

	// 尋找問卷資訊(前台)+
	public QueResponse findProjects(QueRequest queRequest);

	// 作答問卷(前台)+
	public QueResponse filloutReport(QueRequest queRequest);

	// 顯示單筆問卷的多筆填寫時間 回饋-(後台)
	public QueResponse showWriteTime(QueRequest queRequest);

	// 顯示單筆問卷的特定資訊回饋-(後台)
	public QueResponse showReport(QueRequest queRequest);

	// 顯示問卷所有回饋-統計(前後台)
	public QueResponse showAllReport(QueRequest queRequest);

	// vo
	public QueResponse showQuestionnaireInfo(QueRequest queRequest);

}
