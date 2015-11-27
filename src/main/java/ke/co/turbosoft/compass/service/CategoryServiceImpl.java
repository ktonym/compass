package ke.co.turbosoft.compass.service;

import ke.co.turbosoft.compass.entity.Category;
import ke.co.turbosoft.compass.entity.CorpAnniv;
import ke.co.turbosoft.compass.repo.CategoryRepo;
import ke.co.turbosoft.compass.repo.CorpAnnivRepo;
import ke.co.turbosoft.compass.vo.Result;
import ke.co.turbosoft.compass.vo.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by akipkoech on 4/7/15.
 */
@Service("categoryService")
@Transactional
public class CategoryServiceImpl extends AbstractService implements CategoryService {

    @Autowired
    protected CategoryRepo categoryRepo;

    @Autowired
    protected CorpAnnivRepo corpAnnivRepo;

    public CategoryServiceImpl() {
        super();
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Result<Category> store(Integer idCategory,
                                  Integer idCorpAnniv,
                                  char cat,
                                  String description,
                                  String actionUsername) {
        if(!isValidUser(actionUsername)){
            return ResultFactory.getFailResult(USER_INVALID);
        }



         // TODO modify to handle more scenarios of editing an existing category
         // TODO check that category is unique


        Category category;

        if(idCategory == null){
            category = new Category();
        } else {

            category = categoryRepo.findOne(idCategory);

            if(category == null){
                return ResultFactory.getFailResult("Unable to find category with ID [ " + idCategory + " ]");
            }

        }

        category.setCorpAnniv(corpAnnivRepo.findOne(idCorpAnniv));
        category.setCat(cat);
        category.setDescription(description);
        categoryRepo.save(category);
        return ResultFactory.getSuccessResult(category);


    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Result<Category> remove(Integer idCategory, String actionUsername) {

        //TODO complete setup
        return null;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Result<Category> find(Integer idCategory, String actionUsername) {

        //TODO complete setup

        return null;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Result<List<Category>> findAll(String actionUsername) {

        //TODO complete setup

        return null;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Result<List<Category>> findByAnniv(CorpAnniv anniv, String actionUsername) {

        if(!isValidUser(actionUsername)){
            return ResultFactory.getFailResult(USER_INVALID);
        }

        return ResultFactory.getSuccessResult(categoryRepo.findByCorpAnniv(anniv));


    }
}
