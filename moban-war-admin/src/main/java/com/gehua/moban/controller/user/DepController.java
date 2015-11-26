package com.gehua.moban.controller.user;

import com.gehua.moban.common.cons.Constants;
import com.gehua.moban.common.exception.BizException;
import com.gehua.moban.common.utils.CommonUtil;
import com.gehua.moban.model.entity.core.Page;
import com.gehua.moban.model.entity.uc.Dep;
import com.gehua.moban.service.uc.DepService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
* 代码生成器自动生成
* Date:2015-11-16 14:56:15
* @author
*/
@Controller
@RequestMapping("dep")
public class DepController {
	private static final Logger logger = LoggerFactory.getLogger(DepController.class);
	private static final String PREFIX = "uc/";

	@Autowired
	private DepService depService;
	/**
	 * 页面跳转
	 * @return
	 */
	@RequestMapping("toDepPage")
	public String toDepPage() {
		return PREFIX + "dep";
	}

	/**
	 * 获取分页数据
	 * @param page
	 * @return
	 */
	@RequestMapping("page")
	public  @ResponseBody Page<Dep> page(Page<Dep> page){
		try {
			 page =  depService.searchByPage(page);
		} catch (Exception e) {
			 logger.error(e.getMessage(),e);
		}
	    return page;
    }
	/**
	 * 新建数据
	 * @param dep
	 * @return
	 */
	@RequestMapping("add")
	public @ResponseBody ResponseEntity<String> add(Dep dep) {
		if(CommonUtil.isEmpty(dep))
			 return new ResponseEntity<String>(Constants.EX_PARAM,HttpStatus.BAD_REQUEST);
		try {
			depService.create(dep);
			return new ResponseEntity<String>(Constants.PASS_OK,HttpStatus.OK);
		} catch(BizException e){
			 return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new ResponseEntity<String>(Constants.EX_APP,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
   /**
	* 更新数据
	* @param dep
	* @return
	*/
	@RequestMapping("edit")
	public @ResponseBody
	ResponseEntity<String> edit(Dep dep){
		if(null==dep || CommonUtil.isEmpty(dep.getId()))
		return new ResponseEntity<String>(Constants.EX_PARAM, HttpStatus.BAD_REQUEST);

		try {
			depService.update(dep);
			return new ResponseEntity<String>(Constants.PASS_OK,HttpStatus.OK);
		} catch(BizException e){
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new ResponseEntity<String>(Constants.EX_APP,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * 删除数据
	 * @param ids
	 * @return
	 */
	@RequestMapping("delete")
	public @ResponseBody
	ResponseEntity<String> delete(@RequestParam("ids[]") List<Integer> ids){
		if(CommonUtil.isEmpty(ids))
		return new ResponseEntity<String>(Constants.EX_PARAM,HttpStatus.BAD_REQUEST);
		try {
			depService.delete(ids,Dep.class);
			return new ResponseEntity<String>(Constants.PASS_OK,HttpStatus.OK);
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<String>(Constants.EX_APP,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	/**
	 * 根据id获取信息
	 * @param id
	 * @return
	 */
	@RequestMapping("getDepById")
	public @ResponseBody ResponseEntity<Object> getDepById(Integer id) {
		if (null == id)
		return new ResponseEntity<Object>(Constants.EX_PARAM, HttpStatus.BAD_REQUEST);

		try {
			Dep dep = this.depService.searchById(id);
			if (null != dep) {
				return new ResponseEntity<Object>(dep, HttpStatus.OK);
			} else {
				return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
			}
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<Object>(Constants.EX_APP, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}